import './App.css'
import CategoryList from './components/CategoryList'

function App() {
  return (
    <div className="app">
      <header>
        <h1>OrchestrArchive</h1>
      </header>
      <main>
        <h2>Kategorien</h2>
        <CategoryList />
      </main>
    </div>
  )
}

export default App
