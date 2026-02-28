import { Leaf, Droplets } from 'lucide-react';
import './SeasonalAdvice.css';

export function SeasonalAdvice() {
    return (
        <div className="advice-card">
            <div className="flex-center" style={{ gap: '8px', marginBottom: '16px', justifyContent: 'flex-start' }}>
                <Leaf size={20} className="icon-leaf" />
                <h3 className="section-title">Agriculture Advice</h3>
            </div>

            <div className="advice-content">
                <div className="advice-header flex-between">
                    <span className="advice-region">Northern Delta</span>
                    <span className="advice-season">Winter Crop</span>
                </div>

                <p className="advice-text">
                    High chance of heavy rain and localized flooding in the next 3 days.
                    <span className="text-neon" style={{ fontWeight: 500, display: 'block', marginTop: '8px' }}>
                        Avoid sowing seeds or deploying fertilizer at this time.
                    </span>
                </p>

                <div className="advice-metrics">
                    <div className="metric">
                        <Droplets size={16} />
                        <span>Soil Moisture: 85% (High)</span>
                    </div>
                </div>
            </div>
        </div>
    );
}
