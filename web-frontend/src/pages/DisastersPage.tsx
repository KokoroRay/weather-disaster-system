import { SearchBar } from '../components/SearchBar';
import { BentoCard } from '../components/BentoCard';
import { AlertTriangle, MapPin } from 'lucide-react';

export function DisastersPage() {
    const mockDisasters = [
        { id: 1, type: "Bão nhiệt đới", severity: "high", loc: "Đà Nẵng", date: "Hôm nay" },
        { id: 2, type: "Mưa lớn diện rộng", severity: "medium", loc: "Hà Nội", date: "Hôm qua" },
        { id: 3, type: "Ngập lụt", severity: "low", loc: "Hồ Chí Minh", date: "2 ngày trước" },
    ];

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
                        <h2 className="text-secondary" style={{ marginBottom: '16px', display: 'flex', alignItems: 'center', gap: '8px' }}>
                            <AlertTriangle className="text-red-500" /> Cảnh báo thiên tai
                        </h2>
                        <div style={{ display: 'flex', flexDirection: 'column', gap: '16px' }}>
                            {mockDisasters.map(d => (
                                <div key={d.id} style={{ display: 'flex', justifyContent: 'space-between', padding: '16px', background: 'rgba(255,255,255,0.4)', borderRadius: '12px' }}>
                                    <div>
                                        <h4 style={{ color: 'var(--text-primary)', fontWeight: 600 }}>{d.type}</h4>
                                        <p style={{ color: 'var(--text-secondary)', fontSize: '0.9rem', display: 'flex', alignItems: 'center', gap: '4px', marginTop: '4px' }}>
                                            <MapPin size={14} /> {d.loc}
                                        </p>
                                    </div>
                                    <div style={{ textAlign: 'right' }}>
                                        <span style={{
                                            padding: '4px 12px',
                                            borderRadius: '20px',
                                            fontSize: '0.8rem',
                                            background: d.severity === 'high' ? '#fee2e2' : d.severity === 'medium' ? '#fef3c7' : '#e0e7ff',
                                            color: d.severity === 'high' ? '#ef4444' : d.severity === 'medium' ? '#f59e0b' : '#3b82f6',
                                            fontWeight: 600
                                        }}>
                                            Mức độ: {d.severity.toUpperCase()}
                                        </span>
                                        <p style={{ color: 'var(--text-secondary)', fontSize: '0.9rem', marginTop: '8px' }}>{d.date}</p>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                </BentoCard>

                <BentoCard className="area-aqi" delay={0.2} style={{ gridColumn: 'span 4', gridRow: 'span 2' }}>
                    <div style={{ padding: '0', height: '100%', minHeight: '400px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                        <p className="text-secondary">Bản đồ vệ tinh (Placeholder)</p>
                    </div>
                </BentoCard>
            </main>
        </div>
    );
}
