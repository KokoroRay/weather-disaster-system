import { CloudRain, MapPin } from 'lucide-react';
import './MainWeather.css';

export function MainWeather() {
    return (
        <div className="main-weather">
            <div className="location-header">
                <MapPin size={24} className="icon-map-pin" />
                <div>
                    <h2 className="location-city">Hanoi, Vietnam</h2>
                    <p className="location-date">Today, Oct 15</p>
                </div>
            </div>

            <div className="temperature-display">
                <div className="temp-primary">24°</div>
                <div className="temp-details">
                    <div className="temp-condition">Heavy Rain</div>
                    <div className="temp-high-low">H: 26°  L: 22°</div>
                </div>
            </div>

            <div className="weather-visual">
                <CloudRain size={80} className="weather-icon-main" strokeWidth={1} />
            </div>
        </div>
    );
}
