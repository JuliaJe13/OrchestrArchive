import { useState } from 'react'
import Navigation from './components/Navigation'
import SheetMusicSearch from './components/SheetMusicSearch'
import SheetMusicForm from './components/SheetMusicForm'
import SheetMusicList from './components/SheetMusicList'
import SheetMusicDetail from './components/SheetMusicDetail'
import './App.css'

type Tab = 'search' | 'add' | 'all'

function App() {
    const [activeTab, setActiveTab] = useState<Tab>('search')
    const [selectedId, setSelectedId] = useState<number | null>(null)

    return (
        <div className="app">
            <header>
                <h1>OrchestrArchive</h1>
                <p className="subtitle">Notenverwaltung für Orchester und Ensembles</p>
            </header>
            <Navigation activeTab={activeTab} onTabChange={setActiveTab} />
            <main>
                {activeTab === 'search' && (
                    <div>
                        <p className="intro">Durchsuche deine Notensammlung nach Titel, Komponist, Arrangeur und mehr.</p>
                        <SheetMusicSearch />
                    </div>
                )}
                {activeTab === 'add' && (
                    <div>
                        <h2>Neuen Notensatz hinzufügen</h2>
                        <SheetMusicForm />
                    </div>
                )}
                {activeTab === 'all' && (
                    <div>
                        <h2>Alle Notensätze</h2>
                        <SheetMusicList onSelect={setSelectedId} />
                        {selectedId && <SheetMusicDetail id={selectedId} />}
                    </div>
                )}
            </main>
        </div>
    )
}

export default App