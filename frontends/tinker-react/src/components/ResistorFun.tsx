import { useState } from "react";
import { ResistorColor } from "../types";
import { ResistorDisplay } from "./ResistorDisplay.tsx";

import "./ResistorFun.scss";

interface ResistorColorSelectProps {
  name: string;
  value: string;
  onChange: (colorName: string) => void;
}

function ResistorColorSelect({ name, value, onChange }: ResistorColorSelectProps) {
  return (
    <select
      name={name}
      value={value}
      onChange={(e) => onChange(e.target.value)}
    >
      {Object.values(ResistorColor).map((color) => (
        <option key={color.name} value={color.name}>
          {color.name}
        </option>
      ))}
    </select>
  );
}

export const ResistorFun = () => {
  const [bands, setBands] = useState<ResistorColor[]>([
    ResistorColor.Red,
    ResistorColor.Black,
    ResistorColor.Blue,
    ResistorColor.Gold,
  ]);

  const updateBand = (index: number, colorName: string) => {
    const newBands = [...bands];
    const color = Object.values(ResistorColor).find(c => c.name === colorName);
    if (color) {
      newBands[index] = color;
      setBands(newBands);
    }
  };

  return (
    <>
      <div>
        <h1>Resistor Fun</h1>
      </div>
      <div>
        <ResistorColorSelect
          name="first"
          value={bands[0].name}
          onChange={(colorName) => updateBand(0, colorName)}
        />
        <ResistorColorSelect
          name="second"
          value={bands[1].name}
          onChange={(colorName) => updateBand(1, colorName)}
        />
        <ResistorColorSelect
          name="third"
          value={bands[2].name}
          onChange={(colorName) => updateBand(2, colorName)}
        />
        <ResistorColorSelect
          name="fourth"
          value={bands[3].name}
          onChange={(colorName) => updateBand(3, colorName)}
        />
      </div>
      <div>
        <ResistorDisplay bands={bands} />
      </div>
    </>
  );
};
