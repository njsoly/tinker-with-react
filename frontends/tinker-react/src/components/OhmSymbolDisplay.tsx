import { useEffect, useState } from 'react';
import { resistorService } from '../services/resistorService';

export function OhmSymbolDisplay() {
  const [ohmSymbol, setOhmSymbol] = useState<string>('');
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchOhmSymbol = async () => {
      try {
        setLoading(true);
        const symbol = await resistorService.getOhmSymbol();
        setOhmSymbol(symbol);
        setError(null);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'Unknown error');
      } finally {
        setLoading(false);
      }
    };

    fetchOhmSymbol();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Ohm Symbol from Backend: {ohmSymbol}</h2>
    </div>
  );
}
