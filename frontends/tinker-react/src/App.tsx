import { useState } from 'react'
import './App.scss'
import { ResistorFun } from "./components/ResistorFun.tsx";
import { OhmSymbolDisplay } from "./components/OhmSymbolDisplay.tsx";
import { SvgResistorDisplay } from "./components/SvgResistorDisplay.tsx";

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <section id="center">
        <ResistorFun />
        <div className="ticks"></div>
        <SvgResistorDisplay />
        <div className="ticks"></div>
        <div>
          <h1>Hello</h1>
          <p>
            Are we having fun yet?
          </p>
        </div>
        <OhmSymbolDisplay />
        <button
          className="counter"
          onClick={() => setCount((count) => count + 1)}
        >
          Count is {count}
        </button>
      </section>


      <div className="ticks"></div>
      <section id="spacer"></section>
    </>
  )
}

export default App
