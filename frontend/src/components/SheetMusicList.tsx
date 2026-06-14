import { useEffect, useState } from 'react'
import type { SheetMusic } from '../types'
import { getSheetMusicList } from '../api/orchestrArchiveApi'

type SheetMusicListProps = {
    onSelect: (id: number) => void
}

function SheetMusicList({ onSelect }: SheetMusicListProps) {
    const [sheetMusicList, setSheetMusicList] = useState<SheetMusic[]>([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState < string | null > (null)

    useEffect(() => {
        let ignore = false

        getSheetMusicList()
            .then((data) => {
                if (!ignore) {
                    setSheetMusicList(data)
                    setError(null)
                }
            })
            .catch((err) => {
                if (!ignore) {
                    setError(err instanceof Error ? err.message : 'Unbekannter Fehler')
                }
            })
            .finally(() => {
                if (!ignore) {
                    setLoading(false)
                }
            })

        return () => {
            ignore = true
        }
    }, [])
    if (loading) {
        return <p>Lade ...</p>
    }
    if (error) {
        return <p className="error">Fehler beim Laden: {error}</p>
    }
    return (
        <ul className="sheetMusic-list">
            {sheetMusicList.map((obj) => (
                <li key={obj.id}>
                    <strong>{obj.title}</strong>
                    {' - '}{obj.composer}
                    {' ('}{obj.year}{')'}
                    {' - '}{obj.level}
                    <button onClick={() => onSelect(obj.id)}>Details</button>
                </li>
            ))}
        </ul>
    )
}

export default SheetMusicList