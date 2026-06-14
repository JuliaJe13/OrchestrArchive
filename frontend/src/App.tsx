import './App.css'
import CategoryList from './components/CategoryList'
import SheetMusicList from "./components/SheetMusicList.tsx";

function App() {
  return (
    <div className="app">
      <header>
        <h1>OrchestrArchive</h1>
      </header>
      <main>
        <h2>Kategorien</h2>
        <CategoryList />
        <h2>Notensätze</h2>
        <SheetMusicList />
      </main>
    </div>
  )
}

export default App
