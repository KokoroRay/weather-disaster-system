import { Wind, Droplets, Sun, Eye } from 'lucide-react';
import './TodayHighlights.css';

export function TodayHighlights() {
    return (
        <div className="highlights-card">
            <h3 className="section-title" style={{ marginBottom: '20px' }}>Today's Highlights</h3>

            <div className="highlights-grid">
                {/* Wind Status */}
                <div className="highlight-item">
                    <div className="highlight-header text-secondary">
                        <Wind size={16} />
                        <span>Wind Status</span>
                    </div>
                    <div className="highlight-value">
                        <span className="value-number">7.70</span>
                        <span className="value-unit">km/h</span>
                    </div>
                    <div className="highlight-footer mt-auto">
                        <span className="footer-pill">WSW</span>
                    </div>
                </div>

                {/* Humidity */}
                <div className="highlight-item">
                    <div className="highlight-header text-secondary">
                        <Droplets size={16} />
                        <span>Humidity</span>
                    </div>
                    <div className="highlight-value">
                        <span className="value-number">12</span>
                        <span className="value-unit">%</span>
                    </div>
                    <div className="highlight-footer mt-auto flex-between w-full">
                        <span className="text-secondary text-sm">Normal</span>
                    </div>
                </div>

                {/* Visibility */}
                <div className="highlight-item">
                    <div className="highlight-header text-secondary">
                        <Eye size={16} />
                        <span>Visibility</span>
                    </div>
                    <div className="highlight-value">
                        <span className="value-number">5.2</span>
                        <span className="value-unit">km</span>
                    </div>
                    <div className="highlight-footer mt-auto">
                        <span className="text-warning text-sm">Average</span>
                    </div>
                </div>

                {/* UV Index */}
                <div className="highlight-item">
                    <div className="highlight-header text-secondary">
                        <Sun size={16} />
                        <span>UV Index</span>
                    </div>
                    <div className="highlight-value">
                        <span className="value-number">12</span>
                        <span className="value-unit"></span>
                    </div>
                    <div className="highlight-footer mt-auto">
                        {/* Simple visual bar for UV */}
                        <div className="uv-bar-container w-full">
                            <div className="uv-bar indicator-high"></div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
}
