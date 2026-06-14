import { useEffect, useState } from 'react'
import type { Category } from '../types'
import { getCategories } from '../api/orchestrArchiveApi'

function CategoryList() {
    const [categories, setCategories] = useState<Category[]>([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState < string | null > (null)

    useEffect(() => {
        let ignore = false

        getCategories()
            .then((data) => {
                if (!ignore) {
                    setCategories(data)
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
        <ul className="category-list">
            {categories.map((obj) => (
                <li key={obj.id}>
                    <strong>{obj.genre}</strong>
                    {' - '}{obj.description}
                </li>
            ))}
        </ul>
    )
}

export default CategoryList