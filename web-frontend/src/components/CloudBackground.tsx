import './CloudBackground.css';

export function CloudBackground() {
    return (
        <div className="cloud-background">
            <div className="cloud-layer layer-back">
                <div className="real-cloud" style={{ top: '5%', left: '-10%', width: '50vw', height: '40vh', animationDelay: '0s' }}></div>
                <div className="real-cloud" style={{ top: '35%', left: '30%', width: '60vw', height: '30vh', animationDelay: '-40s' }}></div>
                <div className="real-cloud" style={{ top: '20%', left: '70%', width: '55vw', height: '45vh', animationDelay: '-80s' }}></div>
            </div>

            <div className="cloud-layer layer-mid">
                <div className="real-cloud" style={{ top: '15%', left: '10%', width: '45vw', height: '35vh', animationDelay: '-15s' }}></div>
                <div className="real-cloud" style={{ top: '65%', left: '20%', width: '50vw', height: '30vh', animationDelay: '-50s' }}></div>
                <div className="real-cloud" style={{ top: '10%', left: '65%', width: '40vw', height: '25vh', animationDelay: '-70s' }}></div>
            </div>

            <div className="cloud-layer layer-front">
                <div className="real-cloud" style={{ top: '-10%', left: '40%', width: '70vw', height: '50vh', animationDelay: '-25s' }}></div>
                <div className="real-cloud" style={{ top: '75%', left: '-5%', width: '60vw', height: '40vh', animationDelay: '-60s' }}></div>
            </div>
        </div>
    );
}
