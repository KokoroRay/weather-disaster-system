import { AlertTriangle, ChevronRight } from 'lucide-react';
import './DisasterAlert.css';

export function DisasterAlert() {
    return (
        <div className="disaster-alert-card">
            <div className="alert-header">
                <div className="alert-icon-wrapper pulse-danger">
                    <AlertTriangle size={20} className="icon-danger" />
                </div>
                <span className="alert-badge">High Risk</span>
            </div>

            <div className="alert-content">
                <h3 className="alert-title">Flash Flood Warning</h3>
                <p className="alert-desc">Expect heavy rainfall leading to potential flash flooding in low-lying areas. Avoid travel if possible.</p>
                <p className="alert-time">Valid until 08:00 PM, Oct 15</p>
            </div>

            <button className="alert-btn">
                <span>View Details</span>
                <ChevronRight size={16} />
            </button>
        </div>
    );
}
