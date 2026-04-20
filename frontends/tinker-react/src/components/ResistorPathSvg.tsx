import Path, { Svg } from "react-svg-path";
import "./ResistorPathSvg.scss";
const RESISTOR_BODY_WIDTH = 0.7;
const RESISTOR_BODY_HEIGHT = 0.4;

interface ResistorPathSvgProps {
  width: number;
  height: number;
  margin?: number;
  [rest: string]: any;
}

export const ResistorPathSvg = ({
  width,
  height,
  margin = 0,
  ...rest
}: ResistorPathSvgProps) => {
  const pts = {
    topLeft: new DOMPoint(margin, margin),
    bottomRight: new DOMPoint(width - margin, height - margin),
    center: new DOMPoint(width / 2, height / 2)
  };
  const lineOfSymmetryX = (pts.bottomRight.x + pts.topLeft.x) / 2;
  // @ts-ignore
  const lineOfSymmetryY = (pts.bottomRight.y + pts.topLeft.y) * 0.4;

  const centerOfBody = new DOMPoint(lineOfSymmetryX, lineOfSymmetryY);
  const bodySizeY = (pts.bottomRight.y - pts.topLeft.y) * 0.4;
  const bodySizeX = (pts.bottomRight.x - pts.topLeft.x) * 0.7;

  const p = new Path();
  // go to top side, horizontal center of resistor body
  p.moveTo(lineOfSymmetryX, lineOfSymmetryY - bodySizeY/2);
  // p.qCurve(
  //   centerOfBody.x + bodySizeX / 2, centerOfBody.y,
  //   lineOfSymmetryX + bodySizeX * 2 / 7, lineOfSymmetryY - bodySizeY / 2 - 50)
  p.arc(
    bodySizeX / 5,
    bodySizeY / 10,
    0,
    0,
    0,
    lineOfSymmetryX + (bodySizeX * 2 / 7),
    lineOfSymmetryY - (bodySizeY / 2) - (bodySizeY / 10));
  p.arc(
    bodySizeX / 10,
    bodySizeY / 10,
    0,
    0,
    1,
    (bodySizeX * 2 / 7),
    0,
    true
  )






  p.moveTo(0,0);

  return (
    <Svg className="resistorPathSvg" width={width} height={height} {...rest}>
      {p.toComponent()}
    </Svg>
  );
};
