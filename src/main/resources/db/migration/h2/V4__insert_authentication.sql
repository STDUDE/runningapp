INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('read-client', 'resource-server-rest-api',
	/*read-client-password1234*/'$2a$04$o5iB3trG8v7W/RWTqXJDWefwDIOKc.LWe.U5hz9EDei9yXgKVxmcS',
	'read', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('read-write-client', 'resource-server-rest-api',
	/*read-write-client-password1234*/'$2a$04$L/DXMSsQ4QI.y7HGrZYG/ut74IJ5Rjrpo.2iFSsS0/eTRcj1tZuqy',
	'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);
