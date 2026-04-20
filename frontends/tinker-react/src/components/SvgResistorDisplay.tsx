import './SvgResistorDisplay.scss';

export const SvgResistorDisplay = () => {
  const [width, height] = [400, 250];
  /** I decided that I didn't want to call this bandWidth.. */
  const bandW = 20;

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
                 50,100  50,60  55,55  85,55   90,57   95,60  110,65  290,65  305,60  310,57
                315,55  345,55 350,60 350,100 350,144 345,147 315,147 310,145 305,142 290,137
                110,137  95,142 90,145 85,147  55,147  50,142  50,100"
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

          <rect className="resistorBand"
                key={0}
                x={80}
                y={0}
                width={bandW}
                height={height}
                fill="green"
                clipPath="url(#resistorBodyClip)"
          />

        </svg>
      </div>

    </section>
  );
};
