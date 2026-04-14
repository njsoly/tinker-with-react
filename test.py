#!/usr/bin/env python3

"""
################################################################################
# Unified Test Runner for tinker-with-react
################################################################################

PURPOSE:
    Runs tests for all subprojects (backend and frontend) with flexible options

USAGE:
    ./test.py [options]

OPTIONS:
    -h, --help          Show this help message
    --backend-only      Run only backend tests
    --frontend-only     Run only frontend tests
    --continue          Continue running all tests even if some fail
    --skip-resistors    Skip resistors backend tests
    --skip-trading      Skip trading backend tests
    --skip-react        Skip React frontend tests

################################################################################
"""

import argparse
import subprocess
import sys
from pathlib import Path
from typing import List, Tuple

PROJECT_ROOT = Path(__file__).parent.absolute()


class TestResult:
    def __init__(self, project: str, passed: bool, test_count: int, output: str = ""):
        self.project = project
        self.passed = passed
        self.test_count = test_count
        self.output = output


def run_gradle_tests(project_name: str, project_path: Path) -> TestResult:
    """Run Gradle tests for a backend project"""
    print(f"\n{'=' * 70}")
    print(f"  Testing {project_name}")
    print('=' * 70)
    print()

    try:
        result = subprocess.run(
            ["./gradlew", "test"],
            cwd=project_path,
            capture_output=True,
            text=True
        )

        # Parse test count from Gradle output
        test_count = 0
        for line in result.stdout.split('\n'):
            if 'tests completed' in line.lower():
                parts = line.split()
                for i, part in enumerate(parts):
                    if part.isdigit():
                        test_count = int(part)
                        break

        success = result.returncode == 0
        output = result.stdout if success else result.stderr

        if success:
            print(f"✓ {project_name} tests PASSED ({test_count} tests)")
        else:
            print(f"✗ {project_name} tests FAILED")
            print(f"\nError output:\n{result.stderr}")

        return TestResult(project_name, success, test_count, output)

    except Exception as e:
        print(f"✗ Error running {project_name} tests: {e}")
        return TestResult(project_name, False, 0, str(e))


def run_npm_tests(project_name: str, project_path: Path) -> TestResult:
    """Run npm tests for a frontend project"""
    print(f"\n{'=' * 70}")
    print(f"  Testing {project_name}")
    print('=' * 70)
    print()

    # Check if test script exists
    package_json = project_path / "package.json"
    if not package_json.exists():
        print(f"⚠ Skipping {project_name} - no package.json found")
        return TestResult(project_name, True, 0, "No package.json")

    # For now, just check if project builds (many React projects don't have tests set up yet)
    try:
        result = subprocess.run(
            ["npm", "run", "build"],
            cwd=project_path,
            capture_output=True,
            text=True
        )

        success = result.returncode == 0

        if success:
            print(f"✓ {project_name} build PASSED")
        else:
            print(f"✗ {project_name} build FAILED")
            print(f"\nError output:\n{result.stderr}")

        return TestResult(project_name, success, 0, result.stdout if success else result.stderr)

    except Exception as e:
        print(f"✗ Error testing {project_name}: {e}")
        return TestResult(project_name, False, 0, str(e))


def main():
    parser = argparse.ArgumentParser(
        description="Run tests for all tinker-with-react projects",
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog=__doc__
    )
    parser.add_argument("--backend-only", action="store_true", help="Run only backend tests")
    parser.add_argument("--frontend-only", action="store_true", help="Run only frontend tests")
    parser.add_argument("--continue", dest="continue_on_fail", action="store_true",
                        help="Continue running all tests even if some fail")
    parser.add_argument("--skip-resistors", action="store_true", help="Skip resistors tests")
    parser.add_argument("--skip-trading", action="store_true", help="Skip trading tests")
    parser.add_argument("--skip-react", action="store_true", help="Skip React tests")

    args = parser.parse_args()

    results: List[TestResult] = []

    print()
    print("╔═══════════════════════════════════════════════════════════════╗")
    print("║          tinker-with-react Test Suite Runner                 ║")
    print("╚═══════════════════════════════════════════════════════════════╝")

    # Backend tests
    if not args.frontend_only:
        if not args.skip_resistors:
            resistors_path = PROJECT_ROOT / "backends" / "resistors"
            if resistors_path.exists():
                result = run_gradle_tests("resistors", resistors_path)
                results.append(result)
                if not result.passed and not args.continue_on_fail:
                    print("\n✗ Tests failed. Stopping execution.")
                    sys.exit(1)

        if not args.skip_trading:
            trading_path = PROJECT_ROOT / "backends" / "trading"
            if trading_path.exists():
                result = run_gradle_tests("trading", trading_path)
                results.append(result)
                if not result.passed and not args.continue_on_fail:
                    print("\n✗ Tests failed. Stopping execution.")
                    sys.exit(1)

    # Frontend tests
    if not args.backend_only:
        if not args.skip_react:
            react_path = PROJECT_ROOT / "frontends" / "tinker-react"
            if react_path.exists():
                result = run_npm_tests("tinker-react", react_path)
                results.append(result)
                if not result.passed and not args.continue_on_fail:
                    print("\n✗ Tests failed. Stopping execution.")
                    sys.exit(1)

    # Summary
    print()
    print("=" * 70)
    print("  TEST SUMMARY")
    print("=" * 70)
    print()

    total_tests = sum(r.test_count for r in results)
    passed_projects = sum(1 for r in results if r.passed)
    total_projects = len(results)

    for result in results:
        status = "✓ PASSED" if result.passed else "✗ FAILED"
        test_info = f"({result.test_count} tests)" if result.test_count > 0 else ""
        print(f"  {result.project:20s} {status:10s} {test_info}")

    print()
    print(f"Projects: {passed_projects}/{total_projects} passed")
    if total_tests > 0:
        print(f"Total tests run: {total_tests}")
    print()

    if passed_projects == total_projects:
        print("✓ All tests passed!")
        sys.exit(0)
    else:
        print("✗ Some tests failed.")
        sys.exit(1)


if __name__ == "__main__":
    main()
