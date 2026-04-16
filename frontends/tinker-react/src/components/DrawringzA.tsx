/* eslint-disable @typescript-eslint/no-unused-vars */
// noinspection JSUnusedLocalSymbols

import './DrawringzA.scss';
import { Arc, Circle, Line, Svg } from 'react-svg-path';

export const DrawringzA = () => {
  // @ts-expect-error leave me alone
  const stroke: number = 2;
  // @ts-expect-error stop it
  const gray = '#888';

  const width = 200;
  const height = 100;
  const padding = 10; // distance on top and sides to leave empty

  const center: [number, number] = [width / 2, height / 2];




  return (
    <div className="canvassy">
      <p>hi</p>
      <Svg width={200} height={100} x={4} y={4}>
        <Circle cx={50} cy={50} r={45} />
        <Line x1={0} y1={0} x2={100} y2={100} />
        <Arc sx={0} sy={0} rx={100} ry={100} ex={1} ey={10} />
        <i>Could not render SVG.</i>
      </Svg>
    </div>
  )
}
