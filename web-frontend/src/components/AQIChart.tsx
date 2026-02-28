import { Wind } from 'lucide-react';
import './AQIChart.css';

export function AQIChart() {
    return (
        <div className="aqi-card">
            <div className="aqi-header">
                <div className="flex-center" style={{ gap: '8px' }}>
                    <Wind size={20} className="icon-wind" />
                    <h3 className="section-title">Air Quality</h3>
                </div>
                <div className="aqi-badge">Good</div>
            </div>

            <div className="aqi-content">
                <div className="aqi-value text-neon">28</div>
                <div className="aqi-label">AQI (US)</div>

                <div className="aqi-bar-container">
                    <div className="aqi-bar">
                        <div className="aqi-indicator" style={{ left: '15%' }}></div>
                    </div>
                    <div className="aqi-scale">
                        <span>0</span>
                        <span>100</span>
                        <span>200</span>
                        <span>300+</span>
                    </div>
                </div>
            </div>
        </div>
    );
}
