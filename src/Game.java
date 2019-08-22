import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.net.*;
import java.io.*;

public class Game 
{
	public Map map = null;
	public Texture texture = new Texture();
	public int mode = 1;

	public boolean serverState = false;
	public boolean clientState = false;
	public boolean started = false;
    public Server server;
	public Client client;

	public String ip = "localhost";
	public int port = 29869;

	public Game()
	{

	}

	public class Server
	{
		private ServerSocket serverSocket;
		private Socket socket;
		private ObjectInputStream serverInput;
		private ObjectOutputStream serverOutput;
        public boolean isOnline = false;

		public void start() throws IOException
		{
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();

			serverInput = new ObjectInputStream(socket.getInputStream());

            serverOutput = new ObjectOutputStream(socket.getOutputStream());

            isOnline = true;
		}

		public void loop() throws IOException, ClassNotFoundException
        {
            serverInput = new ObjectInputStream(socket.getInputStream());

            String message = (String) serverInput.readObject();
            if (message == "hello")
            {
                System.out.println("They said something!");
            }
        }

		public void stop() throws IOException
        {
            serverSocket.close();
            isOnline = false;
        }


	}

	public class Client
	{

	    private Socket socket;
        private ObjectInputStream clientInput;
        private ObjectOutputStream clientOutput;

        public void connect() throws IOException
        {
            InetAddress serverAddress = InetAddress.getLocalHost();
            socket = null;
            clientInput = null;
            clientOutput = null;
            socket = new Socket(serverAddress.getHostName(), port);
            clientOutput = new ObjectOutputStream(socket.getOutputStream());
            clientInput = new ObjectInputStream(socket.getInputStream());

        }

        public void loop() throws IOException
        {
            clientOutput = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Konekted try me bics");
            clientOutput.writeObject("hello");
        }
    }

    public void networkCode() throws IOException, ClassNotFoundException
    {
        if (started == false)
        {
            if (serverState == true)
            {
                server = new Server();
                server.start();
				System.out.println("server started");
				started = true;
            }
            else
            {
                client = new Client();
                client.connect();
				System.out.println("client started");
				started = true;
            }
        }
        else
        {
            if (serverState == true)
            {
                server.loop();
            }
            else
            {
                client.loop();
            }
        }
		System.out.println("no");
    }

	public void start()
	{
		map = new Map(15,15,0,2);
		map.generate(0);
		Time.tick = 0;
		Time.fullTick = 0;
	}
	
	public void draw(Graphics g)
	{
		if (map != null)
		{
			map.playerList[0].camera.drawDebug(g);

			map.draw(g, mode, texture);
		}

	}
	
	public void keyInput(int key, boolean released, boolean listener, int _player)
	{
		if (map != null)
		{
			if (listener == true)
			{
				//map.player.movinglistener(key,true);
				//map.camera.movement(key);
				switch (key)
				{
					case KeyEvent.VK_C:
						if (mode == 0)
						{
							mode = 1;
						}
						else
						{
							mode = 0;
						}
						break;
				}
			}
			else
			{
				if (map.playerList[_player].alive == true)
				{
					map.playerList[_player].moveInput(key, released, true);
				}
			}
		}
	}
	
	public void restart(int generator)
	{
		if (map != null)
		{
		map.generate(generator);
		Time.tick = 0;
		Time.fullTick = 0;
		}
	}
	
	public void tickMove()
	{
		if (map != null)
		{
			map.actionLoop();
		}
	}
}
