import { BentoCard } from '../components/BentoCard';
import { Github } from 'lucide-react';

export function AuthPage() {
    return (
        <div className="home-container" style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', minHeight: '80vh' }}>
            <BentoCard className="area-main" delay={0.1} style={{ maxWidth: '480px', width: '100%', padding: '40px' }}>
                <div style={{ textAlign: 'center', marginBottom: '32px' }}>
                    <h1 className="text-gradient logo" style={{ margin: '0 0 8px 0', fontSize: '2.5rem' }}>WW<span className="text-blue-500">.</span></h1>
                    <h2 className="text-primary" style={{ margin: 0, fontSize: '1.5rem', fontWeight: 600 }}>Tạo tài khoản</h2>
                    <p className="text-secondary" style={{ marginTop: '8px' }}>Tham gia WeatherWise để nhận cảnh báo sớm</p>
                </div>

                <button style={{ width: '100%', padding: '12px', background: '#fff', border: '1px solid #e2e8f0', borderRadius: '12px', display: 'flex', alignItems: 'center', justifyContent: 'center', gap: '12px', fontWeight: 600, color: '#333', cursor: 'pointer', marginBottom: '16px', boxShadow: 'var(--shadow-sm)' }}>
                    <Github size={20} /> Tiếp tục với Google (Mô phỏng)
                </button>

                <div style={{ display: 'flex', alignItems: 'center', margin: '24px 0', color: 'var(--text-secondary)' }}>
                    <div style={{ flex: 1, borderTop: '1px solid rgba(0,0,0,0.1)' }}></div>
                    <span style={{ padding: '0 12px', fontSize: '0.9rem' }}>hoặc bằng email</span>
                    <div style={{ flex: 1, borderTop: '1px solid rgba(0,0,0,0.1)' }}></div>
                </div>

                <form style={{ display: 'flex', flexDirection: 'column', gap: '16px' }}>
                    <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
                        <label className="text-secondary" style={{ fontSize: '0.9rem' }}>Địa chỉ Email</label>
                        <input type="email" placeholder="name@example.com" style={{ padding: '12px 16px', borderRadius: '12px', border: '1px solid rgba(59, 130, 246, 0.3)', background: 'rgba(255,255,255,0.6)', outline: 'none' }} />
                    </div>
                    <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
                        <label className="text-secondary" style={{ fontSize: '0.9rem' }}>Mật khẩu</label>
                        <input type="password" placeholder="••••••••" style={{ padding: '12px 16px', borderRadius: '12px', border: '1px solid rgba(59, 130, 246, 0.3)', background: 'rgba(255,255,255,0.6)', outline: 'none' }} />
                    </div>
                    <button style={{ width: '100%', padding: '14px', background: 'var(--accent-main)', border: 'none', borderRadius: '12px', color: '#fff', fontWeight: 600, fontSize: '1rem', cursor: 'pointer', marginTop: '8px', boxShadow: '0 4px 15px rgba(59, 130, 246, 0.4)' }}>
                        Đăng ký
                    </button>
                </form>
            </BentoCard>
        </div>
    );
}
