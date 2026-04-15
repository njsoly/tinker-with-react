import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.scss'
import { ResistorFun } from "./components/ResistorFun.tsx";
import { OhmSymbolDisplay } from "./components/OhmSymbolDisplay.tsx";
import { Canvassy } from "./components/Canvassy.tsx";

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <section id="center">
        <ResistorFun />
        <OhmSymbolDisplay />
        <div className="ticks"></div>
        <Canvassy />
        <div className="ticks"></div>
        <div className="hero">
          <img src={heroImg} className="base" width="170" height="179" alt=""/>
          <img src={reactLogo} className="framework" alt="React logo"/>
          <img src={viteLogo} className="vite" alt="Vite logo"/>
        </div>
        <div>
          <h1>Hello</h1>
          <p>
            Are we having fun yet?
          </p>
        </div>
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
