import { NavLink } from 'react-router-dom';
import { Home, CloudRain, AlertTriangle, User } from 'lucide-react';
import './Navigation.css';

export function Navigation() {
    return (
        <nav className="sidebar glass-panel">
            <div className="sidebar-logo">
                <h2 className="text-gradient logo">WW<span className="text-blue-500">.</span></h2>
            </div>

            <div className="nav-links">
                <NavLink to="/" className={({ isActive }) => `nav-item ${isActive ? 'active' : ''}`} end>
                    <Home size={24} />
                    <span className="nav-tooltip">Trang chủ</span>
                </NavLink>

                <NavLink to="/forecast" className={({ isActive }) => `nav-item ${isActive ? 'active' : ''}`}>
                    <CloudRain size={24} />
                    <span className="nav-tooltip">Dự báo</span>
                </NavLink>

                <NavLink to="/disasters" className={({ isActive }) => `nav-item ${isActive ? 'active' : ''}`}>
                    <AlertTriangle size={24} />
                    <span className="nav-tooltip">Thiên tai</span>
                </NavLink>
            </div>

            <div className="nav-footer">
                <NavLink to="/auth" className={({ isActive }) => `nav-item ${isActive ? 'active' : ''}`}>
                    <User size={24} />
                    <span className="nav-tooltip">Tài khoản</span>
                </NavLink>
            </div>
        </nav>
    );
}
