import {useEffect, useState} from "react";
import type {Category, DifficultyLevel, MusicBrainzWork} from "../types.ts";
import {createSheetMusic, getCategories, searchMusicBrainz} from "../api/orchestrArchiveApi.ts";

function SheetMusicForm() {
    // Formular
    const [title, setTitle] = useState('')
    const [composer, setComposer] = useState('')
    const [arranger, setArranger] = useState('')
    const [year, setYear] = useState(0)
    const [publisher, setPublisher] = useState('')
    const [level, setLevel] = useState<DifficultyLevel>('UNTERSTUFE')

    // MusicBrainz
    const [mbResults, setMbResults] = useState<MusicBrainzWork[]>([])
    const [mbLoading, setMbLoading] = useState(false)

    // Categories
    const [categories, setCategories] = useState<Category[]>([])
    const [selectedCategoryIds, setSelectedCategoryIds] = useState<number[]>([])

    // Return
    const [saving, setSaving] = useState(false)
    const [success, setSuccess] = useState(false)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        getCategories().then(setCategories)
    }, [])

    function handleMusicBrainzSearch() {
        setMbLoading(true)
        searchMusicBrainz(title)
            .then(setMbResults)
            .finally(() => setMbLoading(false))
    }

    function handleSelectMbResult(work: MusicBrainzWork) {
        setTitle(work.title)

        const composer = work.relations.find(r => r.type === 'composer')
        if (composer?.artist) setComposer(composer.artist.name)

        const arranger = work.relations.find(r => r.type === 'arranger')
        if (arranger?.artist) setArranger(arranger.artist.name)

        setMbResults([])
    }

    async function handleSubmit() {
        setSaving(true)
        const selectedCategories = categories.filter(c => selectedCategoryIds.includes(c.id))
        createSheetMusic({
            title, composer, arranger, year, publisher, level, categories: selectedCategories
        })
            .then(() => {
                setSuccess(true)
                setTitle(''); setComposer(''); setArranger('')
                setYear(0); setPublisher(''); setLevel("UNTERSTUFE")
            })
            .catch((err) => setError(err instanceof Error ? err.message : 'Fehler'))
            .finally(() => setSaving(false))
    }

    return (
        <div className="forum">
            <label>
                Titel
                <input
                    onKeyDown={(e) => { if (e.key === 'Enter') handleMusicBrainzSearch() }}
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    placeholder="Titel eingeben..."
                />
            </label>
            <button onClick={handleMusicBrainzSearch}>
                Bei MusicBrainz suchen
            </button>

            {mbLoading && <p>Suche läuft...</p>}
            {error && <p className="error">{error}</p>}

            <ul className="results">
                {mbResults.map((obj) => (
                    <li key={obj.id} onClick={() => handleSelectMbResult(obj)} style={{cursor: 'pointer'}}>
                        <strong>{obj.title}</strong>
                        {' - '}{obj.relations.find(r => r.type === 'composer')?.artist?.name}
                    </li>
                ))}
            </ul>
            <label>Komponist
                <input value={composer} onChange={(e) => setComposer(e.target.value)} />
            </label>
            <label>Arrangeur
                <input value={arranger} onChange={(e) => setArranger(e.target.value)} />
            </label>
            <label>Jahr
                <input type="number" min="1000" max="2030" value={year === 0 ? '' : year} onChange={(e) => setYear(Number(e.target.value))} onFocus={() => { if (year === 0) setYear(1700) }} placeholder="z.B. 1889" />
            </label>
            <label>Verlag
                <input value={publisher} onChange={(e) => setPublisher(e.target.value)} />
            </label>
            <label>Schwierigkeit
                <select value={level} onChange={(e) => setLevel(e.target.value as DifficultyLevel)}>
                    <option value="" disabled>Bitte wählen...</option>
                    <option value="UNTERSTUFE">Unterstufe</option>
                    <option value="MITTELSTUFE">Mittelstufe</option>
                    <option value="OBERSTUFE">Oberstufe</option>
                    <option value="HOECHSTSTUFE">Höchststufe</option>
                </select>
            </label>
            <button onClick={handleSubmit} disabled={saving}>
                {saving ? 'Speichert...' : 'Speichern'}
            </button>
            {success && <p>Notensatz gespeichert!</p>}

            <div>
                <p>Kategorien:</p>
                {categories.map((cat) => (
                    <label key={cat.id}>
                        <input
                            type="checkbox"
                            checked={selectedCategoryIds.includes(cat.id)}
                            onChange={(e) => {
                                if (e.target.checked) {
                                    setSelectedCategoryIds([...selectedCategoryIds, cat.id])
                                } else {
                                    setSelectedCategoryIds(selectedCategoryIds.filter(id => id !== cat.id))
                                }
                            }}
                        />
                        {cat.genre}
                    </label>
                ))}
            </div>
        </div>
    )

}

export default SheetMusicForm