/* eslint-disable @typescript-eslint/no-unused-vars */
// noinspection JSUnusedLocalSymbols

import { Arc, Circle, Line, Svg } from 'react-svg-path';
import './DrawringzA.scss';

export const DrawringzA = () => {
  // @ts-expect-error leave me alone
  const stroke: number = 2;
  // @ts-expect-error stop it
  const gray = '#888';

  const width = 200;
  const height = 100;
  // @ts-expect-error x aoeu
  const padding = 10; // distance on top and sides to leave empty

  // @ts-expect-error y aoeu
  const center: [number, number] = [width / 2, height / 2];




  return (
    <div className="canvassy">
      <p>hi</p>
      <Svg width={200} height={100} x={4} y={4}>
        <Circle cx={50} cy={50} r={45} size={100} />
        <Line x1={0} y1={0} x2={100} y2={100} ex={0} ey={0} />
        <Arc sx={0} sy={0} rx={100} ry={100} ex={1} ey={10} />
        <i>Could not render SVG.</i>
      </Svg>
    </div>
  )
}
