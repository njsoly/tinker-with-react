import { Group, Polyline, Rect, RoundedRect, Svg } from 'react-svg-path';
export const Squarey = () => {
  const width = 800;
  const height = 500;
  const padding = 10;
  const roundRectWidth = width/10;
  const roundRectHeight = height/2;
  const leadDimensions = [width/12, height/2];

  return (
  <section className="resistorSvgPlayground">
    <div>
      <Svg
        width={width}
        height={height}
        id="compositeShapesSvg">
        <Group stroke={"tan"} fill={"tan"}>
          <RoundedRect width={roundRectWidth}
                       height={roundRectHeight}
                       cx={roundRectWidth + padding + leadDimensions[0]}
                       cy={height * 2/5}
                       radius={25}/>
          <RoundedRect width={roundRectWidth}
                       height={roundRectHeight}
                       cx={width - (roundRectWidth + padding) - leadDimensions[0]}
                       cy={height * 2/5}
                       radius={25}/>
          <Rect width={width - roundRectWidth*2 - leadDimensions[0]*2}
                height={roundRectHeight * 5/6}
                cx={width/2}
                cy={height * 2/5}/>
        </Group>
      </Svg>
    </div>

    <div>
      <Svg id="polylineSvg"
           width={400}
           height={300}
           fill="tan"
           stroke="cyan">
        <Polyline
          points={[
            [50, 100], [50, 60], [55, 55], [85, 55], [90, 57], [95, 60], [110, 65],
            [290, 65], [305, 60], [310, 57], [315, 55], [345, 55], [350, 60], [350, 100],
            [350, 144], [345, 147], [315, 147], [310, 145], [305, 142], [290, 137],
            [110, 137], [95, 142], [90, 145], [85, 147], [55, 147], [50, 142], [50, 100]
          ]}
          strokeWidth="2"
          stroke="peachpuff"
          paintOrder="fill"/>

      </Svg>
    </div>
  </section>
  );
};
