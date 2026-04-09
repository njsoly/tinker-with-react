import { ResistorColor } from "../types";
import { ResistorDisplay } from "./ResistorDisplay.tsx";

interface ResistorFunProps {
  bands: ResistorColor[];
}

export const ResistorFun = ({ bands }: ResistorFunProps) => {
  return (
    <>
      <div>
        <h1>Resistor Fun</h1>
      </div>
      <div>
        <ResistorDisplay bands={bands} />
      </div>
    </>
  );
};
