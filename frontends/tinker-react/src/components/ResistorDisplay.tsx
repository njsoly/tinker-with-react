import { ResistorColor } from '../types';
import './ResistorDisplay.scss';

interface ResistorDisplayProps {
  bands: ResistorColor[];
}

export const ResistorDisplay = ({ bands }: ResistorDisplayProps) => {
  return (
    <div className="resistor-display">
      <div className="resistor-body">
        <div className="resistor-lead left"></div>
        <div className="resistor-core">
          {bands.map((band, index) => (
            <div
              key={index}
              className="resistor-band"
              style={{ backgroundColor: band.css }}
              title={band.name}
            />
          ))}
        </div>
        <div className="resistor-lead right"></div>
      </div>
    </div>
  );
};
