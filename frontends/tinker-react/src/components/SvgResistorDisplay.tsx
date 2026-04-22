import './SvgResistorDisplay.scss';
import { ResistorColor } from "../types";

interface ResistorDisplayProps {
  width?: number,
  height?: number,
  bands: ResistorColor[];
}

export const SvgResistorDisplay = (
  { width = 400, height = 250, bands = [] }: ResistorDisplayProps) => {
  /** I decided that I didn't want to call this bandWidth.. */
  const bandW = width / 20;

  return (
    <section className="resistorSvgPlayground">
      <div>
        <svg id="polylineSvg"
             viewBox={`0 0 ${width} ${height}`}>
          <defs>
            <clipPath id="resistorBodyClip">
              <polyline
                id="resistorBody"
                points="
                 45,70   50,60
                 55,55   85,55   90,57   95,60  110,65  290,65  305,60  310,57
                315,55  345,55  350,60  355,70  357,100 355,132 350,144 345,147
                315,147 310,145 305,142 290,137
                110,137  95,142  90,145  85,147  55,147  50,142 45,132   42, 100"
              />
            </clipPath>
          </defs>

          <rect id="resistorBody"
                x={0}
                y={0}
                width={width}
                height={height}
                fill="tan"
                clipPath="url(#resistorBodyClip)" />


          {Array.from({ length: bands.length }, (_, i) => (
            <rect className="resistorBand"
                  key={i}
                  x={(width / 5) + i * (bandW + (width / 10))}
                  y={0}
                  width={bandW}
                  height={height}
                  fill={bands[i].css}
                  clipPath="url(#resistorBodyClip)"
            />
          ))}

        </svg>
      </div>

    </section>
  );
};
