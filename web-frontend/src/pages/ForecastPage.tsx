import { SearchBar } from '../components/SearchBar';
import { BentoCard } from '../components/BentoCard';

export function ForecastPage() {
    return (
        <div className="home-container">
            <header className="home-header">
                <SearchBar />
                <div className="user-profile">
                    <div className="avatar">K</div>
                </div>
            </header>

            <main className="bento-grid">
                <BentoCard className="area-main" delay={0.1}>
                    <div style={{ padding: '24px' }}>
                        <h2 className="text-secondary" style={{ marginBottom: '16px' }}>Dự báo 7 ngày tới</h2>
                        <div style={{ height: '300px', display: 'flex', alignItems: 'center', justifyContent: 'center', background: 'rgba(255,255,255,0.3)', borderRadius: '16px' }}>
                            <p className="text-secondary">Interactive Chart Placeholder</p>
                        </div>
                    </div>
                </BentoCard>

                <BentoCard className="area-aqi" delay={0.2} style={{ gridColumn: 'span 4', gridRow: 'span 2' }}>
                    <div style={{ padding: '24px' }}>
                        <h3 className="text-secondary">Xu hướng chất lượng không khí</h3>
                    </div>
                </BentoCard>
            </main>
        </div>
    );
}
