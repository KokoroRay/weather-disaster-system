import { Clock } from 'lucide-react';
import './HourlyForecast.css';

export function HourlyForecast() {
    const hours = [
        { time: 'Now', temp: 24, glow: true },
        { time: '1AM', temp: 23 },
        { time: '2AM', temp: 23 },
        { time: '3AM', temp: 22 },
        { time: '4AM', temp: 21 },
        { time: '5AM', temp: 21 },
    ];

    return (
        <div className="hourly-forecast">
            <div className="flex-center" style={{ gap: '8px', marginBottom: '24px', justifyContent: 'flex-start' }}>
                <Clock size={20} className="icon-clock" />
                <h3 className="section-title">24h Forecast</h3>
            </div>

            <div className="forecast-scroll">
                <div className="forecast-chart-container">
                    {/* A simple SVG curve to represent the temperature graph with neon effect */}
                    <svg viewBox="0 0 500 100" className="neon-chart" preserveAspectRatio="none">
                        <path
                            d="M0,50 Q50,70 100,50 T200,40 T300,60 T400,20 T500,40"
                            fill="none"
                            stroke="var(--accent-main)"
                            strokeWidth="2"
                            className="chart-line"
                        />
                        {/* Glowing dot for current temp */}
                        <circle cx="20" cy="54" r="5" fill="var(--accent-main)" className="chart-dot" />
                    </svg>

                    <div className="forecast-items">
                        {hours.map((hour, idx) => (
                            <div key={idx} className="forecast-item">
                                <span className="forecast-time">{hour.time}</span>
                                <span className={`forecast-temp ${hour.glow ? 'text-blue-600 font-semibold' : ''}`} style={hour.glow ? { color: 'var(--accent-main)' } : {}}>{hour.temp}°</span>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
}
