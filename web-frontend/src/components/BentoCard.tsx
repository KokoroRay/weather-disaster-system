import type { CSSProperties, ReactNode } from 'react';
import { motion } from 'framer-motion';
import './BentoCard.css';

interface BentoCardProps {
    children: ReactNode;
    className?: string;
    gridArea?: string;
    style?: CSSProperties;
    delay?: number;
}

export function BentoCard({ children, className = '', gridArea, style, delay = 0 }: BentoCardProps) {
    const combinedStyle = gridArea ? { gridArea, ...style } : style;

    return (
        <motion.div
            className={`glass-panel bento-card ${className}`}
            style={combinedStyle}
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6, delay, ease: [0.22, 1, 0.36, 1] }}
        >
            {children}
        </motion.div>
    );
}
