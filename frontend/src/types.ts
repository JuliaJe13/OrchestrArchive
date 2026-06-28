export type DifficultyLevel = 'UNTERSTUFE' | 'MITTELSTUFE' | 'OBERSTUFE' | 'HOECHSTSTUFE'

export interface Category {
    id: number
    genre: string
    description: string
}

export interface CategoryInput {
    genre: string
    description: string
}

export interface SheetMusic {
    id: number
    title: string
    composer: string
    arranger: string
    year: number
    publisher: string
    level: DifficultyLevel
    categories: Category[]
}

export interface SheetMusicInput {
    title: string
    composer: string
    arranger: string
    year: number
    publisher: string
    level: DifficultyLevel
    categories: Category[]
}

export interface VoicePart {
    id: number
    instrument: string
    partNumber: number
    instrumentGroup: string
    sheetCount: number
    sheetMusic: SheetMusic
}

export interface VoicePartInput {
    instrument: string
    partNumber: number
    instrumentGroup: string
    sheetCount: number
    sheetMusic: SheetMusic
}

export interface MusicBrainzArtist {
    name: string
}

export interface MusicBrainzRelation {
    type: string
    artist: MusicBrainzArtist | null
}

export interface MusicBrainzWork {
    id: string
    title: string
    relations: MusicBrainzRelation[]
}