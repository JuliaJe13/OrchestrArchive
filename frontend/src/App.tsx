import './App.css'
import {useState} from "react";
import CategoryList from './components/CategoryList'
import SheetMusicList from './components/SheetMusicList.tsx'
import SheetMusicDetail from './components/SheetMusicDetail.tsx'
import SheetMusicSearch from "./components/SheetMusicSearch.tsx";
import SheetMusicForm from "./components/SheetMusicForm.tsx";


function App() {
  const [selectedId, setSelectedId] = useState<number | null>(null)

  return (
    <div className="app">
      <header>
        <h1>OrchestrArchive</h1>
      </header>
      <main>
        <h2>Kategorien</h2>
        <CategoryList />
        <h2>Notensätze</h2>
        <SheetMusicList onSelect={setSelectedId} />
        {selectedId && <SheetMusicDetail id={selectedId} />}
          <h2>Suche</h2>
          <SheetMusicSearch />
          <h2>Neuen Notensatz hinzufügen</h2>
          <SheetMusicForm />
      </main>
    </div>
  )
}

export default App
