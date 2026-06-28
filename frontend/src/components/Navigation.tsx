type Tab = 'search' | 'add' | 'all'

type NavigationProps = {
    activeTab: Tab
    onTabChange: (tab: Tab) => void
}

function Navigation({ activeTab, onTabChange }: NavigationProps) {
    return (
        <nav className="nav">
            <button
                className={activeTab === 'search' ? 'nav-btn active' : 'nav-btn'}
                onClick={() => onTabChange('search')}
            >
                Suche
            </button>
            <button
                className={activeTab === 'add' ? 'nav-btn active' : 'nav-btn'}
                onClick={() => onTabChange('add')}
            >
                Hinzufügen
            </button>
            <button
                className={activeTab === 'all' ? 'nav-btn active' : 'nav-btn'}
                onClick={() => onTabChange('all')}
            >
                Alle Stücke
            </button>
        </nav>
    )
}

export default Navigation