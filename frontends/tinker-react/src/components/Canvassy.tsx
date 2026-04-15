
export const Canvassy = () => {
    return (
      <div>
        hi

        <canvas>
          <script>
            const canvas = document.querySelector('canvas');
            const ctx = canvas.getContext('2d');
            ctx.fillStyle = 'red';
            ctx.fillRect(0, 0, 100, 100);
          </script>
        </canvas>
        <svg>
          <circle cx="50" cy="50" r="45" />
          <line x1="0" y1="0" x2="100" y2="100" />

        </svg>
      </div>
    )
}
