import './CloudBackground.css';

export function CloudBackground() {
    return (
        <div className="cloud-background">
            <div className="bg-gradient-sky"></div>

            {/* Background Layer: Slow and hazy */}
            <div className="cloud-layer layer-back">
                <div className="css-cloud c1"></div>
                <div className="css-cloud c2"></div>
                <div className="css-cloud c3"></div>
            </div>

            {/* Mid Layer: Medium speed and opacity */}
            <div className="cloud-layer layer-mid">
                <div className="css-cloud c4"></div>
                <div className="css-cloud c5"></div>
            </div>

            {/* Foreground Layer: Faster and distinct */}
            <div className="cloud-layer layer-front">
                <div className="css-cloud c6"></div>
                <div className="css-cloud c7"></div>
            </div>
        </div>
    );
}
