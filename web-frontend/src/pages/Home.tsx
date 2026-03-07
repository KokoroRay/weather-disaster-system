import { BentoCard } from '../components/BentoCard';
import { MainWeather } from '../components/MainWeather';
import { AQIChart } from '../components/AQIChart';
import { HourlyForecast } from '../components/HourlyForecast';
import { TodayHighlights } from '../components/TodayHighlights';
import { DisasterAlert } from '../components/DisasterAlert';
import { SeasonalAdvice } from '../components/SeasonalAdvice';
import { SearchBar } from '../components/SearchBar';
import './Home.css';

export function Home() {
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
                    <MainWeather />
                </BentoCard>

                <BentoCard className="area-aqi" delay={0.2}>
                    <AQIChart />
                </BentoCard>

                <BentoCard className="area-alert" delay={0.3}>
                    <DisasterAlert />
                </BentoCard>

                <BentoCard className="area-highlights" delay={0.4}>
                    <TodayHighlights />
                </BentoCard>

                <BentoCard className="area-hourly" delay={0.5}>
                    <HourlyForecast />
                </BentoCard>

                <BentoCard className="area-advice" delay={0.6}>
                    <SeasonalAdvice />
                </BentoCard>
            </main>
        </div>
    );
}
