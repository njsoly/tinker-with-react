export interface ColorInfo {
  name: string;
  value: number;
  multiplier?: number;
  tolerance?: number;
  tempCoefficient?: number;
}

export interface BandPattern {
  colors: string[];
  bandCount: number;
}

export interface ResistanceEvaluation {
  resistance: number;
  tolerance?: number;
  tempCoefficient?: number;
  formattedValue: string;
  unit: string;
}
