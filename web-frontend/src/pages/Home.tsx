import { BentoCard } from '../components/BentoCard';
import { MainWeather } from '../components/MainWeather';
import { AQIChart } from '../components/AQIChart';
import { HourlyForecast } from '../components/HourlyForecast';
import { TodayHighlights } from '../components/TodayHighlights';
import { DisasterAlert } from '../components/DisasterAlert';
import { SeasonalAdvice } from '../components/SeasonalAdvice';
import { CloudBackground } from '../components/CloudBackground';
import './Home.css';

export function Home() {
    return (
        <>
            <CloudBackground />
            <div className="home-container">
                <header className="home-header">
                    <h1 className="text-gradient logo">WeatherWise<span className="text-blue-500">.</span></h1>
                    <div className="user-profile">
                        <div className="avatar">K</div>
                    </div>
                </header>

                <main className="bento-grid">
                    <BentoCard className="area-main">
                        <MainWeather />
                    </BentoCard>

                    <BentoCard className="area-aqi">
                        <AQIChart />
                    </BentoCard>

                    <BentoCard className="area-hourly">
                        <HourlyForecast />
                    </BentoCard>

                    <BentoCard className="area-highlights">
                        <TodayHighlights />
                    </BentoCard>

                    <BentoCard className="area-alert">
                        <DisasterAlert />
                    </BentoCard>

                    <BentoCard className="area-advice">
                        <SeasonalAdvice />
                    </BentoCard>
                </main>
            </div>
        </>
    );
}
