import {useState} from "react";
import type {SheetMusic} from "../types.ts";
import {
    searchSheetMusicByArranger,
    searchSheetMusicByComposer, searchSheetMusicByPublisher,
    searchSheetMusicByTitle
} from "../api/orchestrArchiveApi.ts";


function SheetMusicSearch() {
    const [searchTerm, setSearchTerm] = useState('')
    const [results, setResults] = useState<SheetMusic[]>([])
    const [loading, setLoading] =useState(false)
    const [error, setError] = useState<string | null>(null)

    function handleSearch() {
        setLoading(true)

        Promise.all([
            searchSheetMusicByTitle(searchTerm),
            searchSheetMusicByComposer(searchTerm),
            searchSheetMusicByArranger(searchTerm),
            searchSheetMusicByPublisher(searchTerm),
        ])
            .then((results) => {
                const combined = results.flat()
                const unique = combined.filter(
                    (obj, index, self) => self.findIndex((o) => o.id === obj.id) === index
                )
                setResults(unique)
                setError(null)
            })
            .catch((err) => {
                setError(err instanceof Error ? err.message : 'Nichts gefunden...')
            })
            .finally(() => {
                setLoading(false)
            })
    }

    return (
        <div className="search">
            <input
                onKeyDown={(e) => { if (e.key === 'Enter') handleSearch() }}
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                placeholder="Titel suchen..."
            />
            <button onClick={handleSearch}>Suchen</button>

            {loading && <p>Suche läuft...</p>}
            {error && <p className="error">{error}</p>}

            <ul className="results">
                {results.map((obj) => (
                    <li key={obj.id}>
                        <strong>{obj.title}</strong>
                        {' - '}{obj.composer}
                        {' ('}{obj.year}{')'}
                        {' - '}{obj.level}
                    </li>
                ))}
            </ul>
        </div>
    )
}

export default SheetMusicSearch