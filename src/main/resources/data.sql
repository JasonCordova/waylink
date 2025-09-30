INSERT INTO URLS (ID, SHORT_URL, LONG_URL, CREATED_AT) VALUES
    ('11111111-1111-1111-1111-111111111111', 'abc123', 'https://www.google.com', NOW() - INTERVAL '10 days'),
    ('22222222-2222-2222-2222-222222222222', 'xyz789', 'https://www.github.com', NOW() - INTERVAL '7 days'),
    ('33333333-3333-3333-3333-333333333333', 'link42', 'https://www.openai.com', NOW() - INTERVAL '5 days'),
    ('44444444-4444-4444-4444-444444444444', 'hello7', 'https://www.reddit.com', NOW() - INTERVAL '2 days'),
    ('55555555-5555-5555-5555-555555555555', 'qwerty', 'https://news.ycombinator.com', NOW() - INTERVAL '1 day');

INSERT INTO RECORDS (ID, URL_ID, CLICKED_AT, TIMEZONE) VALUES
    ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '11111111-1111-1111-1111-111111111111', NOW() - INTERVAL '9 days', 'America/New_York'),
    ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '11111111-1111-1111-1111-111111111111', NOW() - INTERVAL '8 days', 'Europe/London'),
    ('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', '22222222-2222-2222-2222-222222222222', NOW() - INTERVAL '6 days', 'Asia/Tokyo'),
    ('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', '22222222-2222-2222-2222-222222222222', NOW() - INTERVAL '5 days', 'America/Chicago'),
    ('ccccccc1-cccc-cccc-cccc-ccccccccccc1', '33333333-3333-3333-3333-333333333333', NOW() - INTERVAL '3 days', 'America/Los_Angeles'),
    ('ccccccc2-cccc-cccc-cccc-ccccccccccc2', '33333333-3333-3333-3333-333333333333', NOW() - INTERVAL '2 days', 'UTC'),
    ('ccccccc3-cccc-cccc-cccc-ccccccccccc3', '33333333-3333-3333-3333-333333333333', NOW() - INTERVAL '1 day', 'Asia/Kolkata'),
    ('ddddddd1-dddd-dddd-dddd-ddddddddddd1', '44444444-4444-4444-4444-444444444444', NOW() - INTERVAL '1 day', 'Europe/Paris'),
    ('eeeeeee1-eeee-eeee-eeee-eeeeeeeeeee1', '55555555-5555-5555-5555-555555555555', NOW(), 'America/Denver');
