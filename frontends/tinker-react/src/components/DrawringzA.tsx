/* eslint-disable @typescript-eslint/no-unused-vars */
// noinspection JSUnusedLocalSymbols,ES6UnusedImports

import { Arc, Circle, Line, Svg } from 'react-svg-path';
// @ts-expect-error I intend to use this
import { motion } from 'framer-motion';
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
      <Svg width={200} height={100} x={4} y={4} fill={"none"} stroke={"darkCyan"}>
        <Circle cx={50} cy={50} r={7} size={100} stroke={"purple"} />
        <Line x1={0} y1={0} x2={100} y2={100} ex={0} ey={0} />
        <Arc sx={200} sy={100} rx={100} ry={100} ex={1} ey={10} stroke={"white"} />
        <i>Could not render SVG.</i>
      </Svg>
    </div>
  )
}
