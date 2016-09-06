/* Alexandre Maros - D14128553
 * Class C
 */

#include "unp.h"

int main(int argc, char **argv)
{
    int                 listenfd, connfd, n;
    struct sockaddr_in  servaddr;
    char                in_buff[MAXLINE];
    char				out_buff[MAXLINE];
    char				user[64], pass[64];
    char                cliIp[MAXLINE];
    struct sockaddr_in  cliaddr;
    socklen_t           len;

    if (argc != 2)
        err_quit("Usage: ./x <PortNumber>");

    listenfd = Socket(AF_INET, SOCK_STREAM, 0);

    bzero(&servaddr, sizeof(servaddr));
    servaddr.sin_family      = AF_INET;
    servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
    servaddr.sin_port        = htons(atoi(argv[1]));

    Bind(listenfd, (SA *) &servaddr, sizeof(servaddr));

    Listen(listenfd, LISTENQ);

    for(;;)
    {
        len = sizeof(cliaddr);
        connfd = Accept(listenfd, (SA *) &cliaddr, &len);
        inet_ntop(AF_INET, &cliaddr.sin_addr, cliIp, sizeof(cliIp));

        while( (n = read(connfd, in_buff, MAXLINE)) > 0)
		{
			if (strstr(in_buff, "\r\n\r\n"))
			{
				break;
			}
		}
		if (n < 0)
			err_sys("read error");

		sscanf(in_buff, "username: %s password: %s", user, pass);
        if (strcmp(user, "admin") == 0 && strcmp(pass, "pass") == 0)
		{
			snprintf(out_buff, sizeof(out_buff),
            "PROCEED, Your IP Address is: %s and your Port Number is: %d\r\n",
            cliIp, ntohs(cliaddr.sin_port));
		}
		else
		{
			snprintf(out_buff, sizeof(out_buff), "DENIED\r\n");
		}

		Write(connfd, out_buff, strlen(out_buff));

        Close(connfd);
    }
    return 0;
}
