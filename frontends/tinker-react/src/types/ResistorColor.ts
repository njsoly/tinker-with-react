export const ResistorColor = {
  Black: { name: 'Black', css: '#000000' },
  Brown: { name: 'Brown', css: '#8B4513' },
  Red: { name: 'Red', css: '#FF0000' },
  Orange: { name: 'Orange', css: '#FFA500' },
  Yellow: { name: 'Yellow', css: '#FFFF00' },
  Green: { name: 'Green', css: '#008000' },
  Blue: { name: 'Blue', css: '#0000FF' },
  Violet: { name: 'Violet', css: '#8B00FF' },
  Gray: { name: 'Gray', css: '#808080' },
  White: { name: 'White', css: '#FFFFFF' },
  Gold: { name: 'Gold', css: '#FFD700' },
  Silver: { name: 'Silver', css: '#C0C0C0' },
} as const;

export type ResistorColor = typeof ResistorColor[keyof typeof ResistorColor];
