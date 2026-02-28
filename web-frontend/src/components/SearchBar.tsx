import { Search } from 'lucide-react';
import './SearchBar.css';

export function SearchBar() {
    return (
        <div className="search-bar-container glass-panel">
            <Search size={20} className="search-icon text-secondary" />
            <input
                type="text"
                className="search-input"
                placeholder="Tra cứu thời tiết theo vùng, tỉnh thành..."
            />
        </div>
    );
}
