import type {
    Category, CategoryInput, SheetMusic, SheetMusicInput, VoicePart, VoicePartInput, DifficultyLevel,
    MusicBrainzWork
} from '../types'

const BASE = '/api'

async function handle<T>(response: Response): Promise<T> {
    if (!response.ok) {
        throw new Error(`HTTP ${response.status}`)
    }
    return (await response.json()) as T
}

// Category
export function getCategories(): Promise<Category[]> {
    return fetch(`${BASE}/categories`).then((res) => handle<Category[]>(res))
}
export function getCategoryById(id: number): Promise<Category> {
    return fetch(`${BASE}/categories/${id}`).then((res) => handle<Category>(res))
}
export function createCategory(data: CategoryInput): Promise<Category> {
    return fetch(`${BASE}/categories`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    }).then((res) => handle<Category>(res))
}
export function updateCategory(id: number, data: CategoryInput): Promise<Category> {
    return fetch(`${BASE}/categories/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    }).then((res) => handle<Category>(res))
}
export function deleteCategory(id: number): Promise<void> {
    return fetch(`${BASE}/categories/${id}`, {
        method: 'DELETE'
    }).then((res) => {
        if (!res.ok) {
            throw new Error(`HTTP ${res.status}`)
        }
    })
}

// SheetMusic
export function getSheetMusicList(): Promise<SheetMusic[]> {
    return fetch(`${BASE}/sheet-music`).then((res) => handle<SheetMusic[]>(res))
}

export function getSheetMusicById(id: number): Promise<SheetMusic> {
    return fetch(`${BASE}/sheet-music/${id}`).then((res) => handle<SheetMusic>(res))
}
export function createSheetMusic(data: SheetMusicInput): Promise<SheetMusic> {
    return fetch(`${BASE}/sheet-music`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then((res) => handle<SheetMusic>(res))
}
export function updateSheetMusic(id:number, data: SheetMusicInput): Promise<SheetMusic> {
    return fetch(`${BASE}/sheet-music/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then((res) => handle<SheetMusic>(res))
}
export function deleteSheetMusic(id: number): Promise<void> {
    return fetch(`${BASE}/sheet-music/${id}`, {
        method: 'DELETE'
    }).then((res) => {
        if (!res.ok) {
            throw new Error(`HTTP ${res.status}`)
        }
    })
}

// SheetMusic Search
export function searchSheetMusicByTitle(title: string): Promise<SheetMusic[]> {
    return fetch(`${BASE}/sheet-music/search/title?title=${encodeURIComponent(title)}`).then((res) => handle<SheetMusic[]>(res))
}
export function searchSheetMusicByComposer(composer: string): Promise<SheetMusic[]> {
    return fetch(`${BASE}/sheet-music/search/composer?composer=${encodeURIComponent(composer)}`).then((res) => handle<SheetMusic[]>(res))
}
export function searchSheetMusicByArranger(arranger: string): Promise<SheetMusic[]> {
    return fetch(`${BASE}/sheet-music/search/arranger?arranger=${encodeURIComponent(arranger)}`).then((res) => handle<SheetMusic[]>(res))
}
export function searchSheetMusicByPublisher(publisher: string): Promise<SheetMusic[]> {
    return fetch(`${BASE}/sheet-music/search/publisher?publisher=${encodeURIComponent(publisher)}`).then((res) => handle<SheetMusic[]>(res))
}
export function searchSheetMusicByYear(year: number): Promise<SheetMusic[]> {
    return fetch(`${BASE}/sheet-music/search/year?year=${encodeURIComponent(year)}`).then((res) => handle<SheetMusic[]>(res))
}
export function searchSheetMusicByLevel(level: DifficultyLevel): Promise<SheetMusic[]> {
    return fetch(`${BASE}/sheet-music/search/level?level=${encodeURIComponent(level)}`).then((res) => handle<SheetMusic[]>(res))
}


// VoicePart
export function getVoiceParts(): Promise<VoicePart[]> {
    return fetch(`${BASE}/voice-parts`).then((res) => handle<VoicePart[]>(res))
}

export function getVoicePartById(id: number): Promise<VoicePart> {
    return fetch(`${BASE}/voice-parts/${id}`).then((res) => handle<VoicePart>(res))
}
export function createVoicePart(data: VoicePartInput): Promise<VoicePart> {
    return fetch(`${BASE}/voice-parts`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then((res) => handle<VoicePart>(res))
}
export function updateVoicePart(id: number, data: VoicePartInput): Promise<VoicePart> {
    return fetch(`${BASE}/voice-parts/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then((res) => handle<VoicePart>(res))
}
export function deleteVoicePart(id: number): Promise<void> {
    return fetch(`${BASE}/voice-parts/${id}`, {
        method: 'DELETE'
    }).then((res) => {
        if (!res.ok) {
            throw new Error(`HTTP ${res.status}`)
        }
    })
}

// VoicePart Search
export function searchVoicePartsByInstrument(instrument: string): Promise<VoicePart[]> {
    return fetch(`${BASE}/voice-parts/search/instrument?instrument=${encodeURIComponent(instrument)}`).then((res) => handle<VoicePart[]>(res))
}
export function searchVoicePartsByInstrumentGroup(instrumentGroup: string): Promise<VoicePart[]> {
    return fetch(`${BASE}/voice-parts/search/instrument-group?instrumentgroup=${encodeURIComponent(instrumentGroup)}`).then((res) => handle<VoicePart[]>(res))
}

// MusicBrainz: Add SheetMusic
export function searchMusicBrainz(title: string): Promise<MusicBrainzWork[]> {
    return fetch(`${BASE}/music-search?title=${encodeURIComponent(title)}`)
        .then((res) => handle<MusicBrainzWork[]>(res))
}