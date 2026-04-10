import { ResistorColor } from "../types";
import { ResistorDisplay } from "./ResistorDisplay.tsx";

interface ResistorFunProps {
  bands: ResistorColor[];
}

function ResistorColorSelect(props: { name: string }) {
  return (
    <select name={props.name} >
      {Object.values(ResistorColor).map((color) => (
        <option key={color.name} value={color.name}>
          {color.name}
        </option>
      ))}
    </select>
  );
};

export const ResistorFun = ({ bands }: ResistorFunProps) => {
  return (
    <>
      <div>
        <h1>Resistor Fun</h1>
      </div>
      <div>
        <ResistorColorSelect name="first" />
        <ResistorColorSelect name="second" />
        <ResistorColorSelect name="third" />
        <ResistorColorSelect name="fourth" />
      </div>
      <div>
        <ResistorDisplay bands={bands} />
      </div>
    </>
  );
};
