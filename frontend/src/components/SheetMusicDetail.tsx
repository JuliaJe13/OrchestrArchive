import {useEffect, useState} from "react";
import type {SheetMusic, VoicePart} from "../types.ts";
import {getSheetMusicById, getVoiceParts} from "../api/orchestrArchiveApi.ts";

type SheetMusicDetailProps = {
    id: number
}

function SheetMusicDetail({ id }: SheetMusicDetailProps) {
    const [sheetMusic, setSheetMusic] = useState<SheetMusic | null>(null)
    const [voiceParts, setVoiceParts] = useState<VoicePart[]>([])
    const [loading, setLoading] = useState(true)
    const[error, setError] = useState<string | null>(null)

    useEffect(() => {
        let ignore = false
        setLoading(true)

        // 1. SheetMusic laden
        getSheetMusicById(id)
            .then((data) => {
                if (!ignore) {
                    setSheetMusic(data)
            }
        })
        .catch((err) => {
            if (!ignore) {
                setError(err instanceof Error ? err.message : 'Fehler')
            }
        })

        //2. VoiceParts laden
        getVoiceParts()
            .then((data) => {
                if (!ignore) {
                    setVoiceParts(data.filter((vp) =>vp.sheetMusic.id === id))
                    setLoading(false)
                }
            })
            .catch((err) => {
                if (!ignore) {
                    setError(err instanceof Error ? err.message : 'Fehler')
                    setLoading(false)
                }
            })

    return () => { ignore = true}
    }, [id])
    if (loading) {
        return <p>Lade ...</p>
    }
    if (error) {
        return <p className="error">Fehler beim Laden: {error}</p>
    }
    if (!sheetMusic) {
        return null
    }
    return (
        <div className="sheetMusic">
            <h3>{sheetMusic.title}</h3>
            <p>Komponist: {sheetMusic.composer}</p>
            <p>Arrangeur: {sheetMusic.arranger}</p>
            <p>Jahr: {sheetMusic.year}</p>
            {/* publisher unnecessairy for now*/}
            {/* <p>Verlag: {sheetMusic.publisher}</p> */}
            <p>Schwierigkeit: {sheetMusic.level}</p>

            <h4>Stimme</h4>
            {voiceParts.length === 0 ? (
                <p>Keine Stimmen vorhanden</p>
            ) : (
                <ul>
                    {voiceParts.map((vp) => (
                        <li key={vp.id}>
                            {vp.instrument} {vp.partNumber} - {vp.instrumentGroup}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    )
}

export default SheetMusicDetail