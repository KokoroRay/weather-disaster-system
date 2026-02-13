INSERT INTO locations (city_code, city_name, region, latitude, longitude) VALUES ('HANOI', 'Hanoi', 'North', 21.0285, 105.8542) ON CONFLICT (city_code) DO NOTHING;
INSERT INTO locations (city_code, city_name, region, latitude, longitude) VALUES ('HCM', 'Ho Chi Minh City', 'South', 10.8231, 106.6297) ON CONFLICT (city_code) DO NOTHING;
INSERT INTO locations (city_code, city_name, region, latitude, longitude) VALUES ('DNA', 'Da Nang', 'Central', 16.0544, 108.2022) ON CONFLICT (city_code) DO NOTHING;

-- IATA Codes for potentially automated ingestion
INSERT INTO locations (city_code, city_name, region, latitude, longitude) VALUES ('HAN', 'Hanoi - Noi Bai', 'North', 21.0285, 105.8542) ON CONFLICT (city_code) DO NOTHING;
INSERT INTO locations (city_code, city_name, region, latitude, longitude) VALUES ('SGN', 'Ho Chi Minh - Tan Son Nhat', 'South', 10.8231, 106.6297) ON CONFLICT (city_code) DO NOTHING;
INSERT INTO locations (city_code, city_name, region, latitude, longitude) VALUES ('DAD', 'Da Nang - International', 'Central', 16.0544, 108.2022) ON CONFLICT (city_code) DO NOTHING;
