import React from 'react';
import './BentoCard.css';

interface BentoCardProps {
    children: React.ReactNode;
    className?: string;
    gridArea?: string; // Optional CSS grid area for layout
    style?: React.CSSProperties;
}

export function BentoCard({ children, className = '', gridArea, style }: BentoCardProps) {
    const combinedStyle = gridArea ? { gridArea, ...style } : style;

    return (
        <div
            className={`glass-panel bento-card ${className}`}
            style={combinedStyle}
        >
            {children}
        </div>
    );
}
