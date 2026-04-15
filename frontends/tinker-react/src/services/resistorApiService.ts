import { config } from '../config/env';
import type { ColorInfo, BandPattern, ResistanceEvaluation } from '../types';

const API_BASE_URL = config.resistorsApiUrl;

export const resistorApiService = {
  async getOhmSymbol(): Promise<string> {
    const response = await fetch(`${API_BASE_URL}/ohm-symbol`);
    if (!response.ok) {
      throw new Error(`Failed to fetch ohm symbol: ${response.statusText}`);
    }
    return response.text();
  },

  async getColors(): Promise<ColorInfo[]> {
    const response = await fetch(`${API_BASE_URL}/colors`);
    if (!response.ok) {
      throw new Error(`Failed to fetch colors: ${response.statusText}`);
    }
    return response.json();
  },

  async evaluateResistance(bandPattern: BandPattern): Promise<ResistanceEvaluation> {
    const response = await fetch(`${API_BASE_URL}/evaluate`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bandPattern),
    });
    if (!response.ok) {
      throw new Error(`Failed to evaluate resistance: ${response.statusText}`);
    }
    return response.json();
  },
};
