

import java.awt.*;

//	map class
/*

	contains the map class and item class
	
*/
public class Map
{
    public static int length = 21;
    public static int length_z = 21;

    public static Item[][][] map;
    public static int maxPlayers;
    public static Player[] playerList;

    public static class Item
    {
        public int id;
        public int dataTag;

        public Bomb bomb;
        public boolean hasBomb;
        public int planted;

        public PowerUp powerUp;
        public boolean hasPowerUp;

        public Item()
        {
            id = 0;
            dataTag = 0;

            planted = 0;
            hasBomb = false;
            hasPowerUp = false;
        }

    }

    public static abstract class MapDrawing
    {
        private int item_width = 36;
        public int screen_px = 140;
        public int screen_py = 120;

        private void drawItemTexture(Graphics g, int x, int y, int id,int dataTag, Texture texture, float alpha)
        {
            int item_width_c = item_width;

            Composite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER , alpha );
            ((Graphics2D) g).setComposite(comp);

            if (id != 0 && id != 5)
            {

                g.drawImage(texture.SpriteSheet[id][dataTag],x-item_width_c/2,y-item_width_c/2,item_width_c,item_width_c,null);
                //g.drawRect(x-item_width_c/2,y-item_width_c/2,item_width_c,item_width_c);
            }


        }

        public void drawDebug(Graphics g)
        {
            g.drawString("tick: "+String.valueOf(Time.tick),screen_px-50, 50);

            for (int i = 0; i < maxPlayers; i++)
            {

                Player iPlayer =  playerList[i];

                if (iPlayer.color == 2)
                {
                    g.setColor(Color.RED);
                }
                else
                {
                    g.setColor(Color.CYAN);
                }



                //g.drawString("Camera"+String.valueOf(i)+"Angle: "+String.valueOf(iPlayer.camera.angle),screen_px-30+(100*i), 120);
                //g.drawString("Camera"+String.valueOf(i)+": "+String.valueOf(iPlayer.camera.x)+";"+String.valueOf(iPlayer.camera.y)+";"+String.valueOf(iPlayer.camera.z),screen_px-30+(100*i), 100);
                //g.drawString("Player"+String.valueOf(i)+": "+String.valueOf(iPlayer.x)+";"+String.valueOf(iPlayer.y)+";"+String.valueOf(iPlayer.z),screen_px-30+(100*i), 40+530);


                g.drawString("Player"+i+": Health: "+iPlayer.health+" Speed: "+iPlayer.speed+" Bomb size: "+iPlayer.bombSize+" Bomb amount: "+iPlayer.maxBombCount,screen_px-50, screen_py+430+(20*i));

            }
        }

        public void drawMapOrder(Graphics g, Texture SpriteSheet, Camera camera, boolean alpha)
        {

            int camX = camera.x;
            int camY = camera.y;
            int camX_rs = camera.x_rs;
            int camY_rs = camera.y_rs;
            int[] cell;
            cell = new int[3];
            cell[0] = -1;
            cell[1] = -2;
            cell[2] = camera.z;


            int[] cellR;
            cellR = new int[3];
            cellR[0] = 0;
            cellR[1] = 0;
            cellR[2] = 0;

            switch (camera.angle)
            {
                case 0:
                    camX = camera.x;
                    camY = camera.y;
                    camX_rs = camera.x_rs;
                    camY_rs = camera.y_rs;
                    cell[0] = -1;
                    cell[1] = -2;
                    cell[2] = camera.z;


                    break;

                case 1:
                    camX = camera.x;
                    camY = camera.z;
                    camX_rs = camera.x_rs;
                    camY_rs = camera.z_rs;
                    cell[0] = -1;
                    cell[1] = camera.y;
                    cell[2] = -2;
                    break;

                case 2:
                    camX = camera.z;
                    camY = camera.y;
                    camX_rs = camera.z_rs;
                    camY_rs = camera.y_rs;
                    cell[0] = camera.x;
                    cell[1] = -2;
                    cell[2] = -1;
                    break;

            }
            if (alpha == true)
            {
                for (int i2 = -1; i2 <= 1; i2++)
                {
                    for (int i = camX - camX_rs - 1; i < camX + camX_rs; i++)
                    {
                        for (int i1 = camY - camY_rs - 1; i1 < camY + camY_rs; i1++)
                        {

                            cellR = cellSet(i, i1, cell, i2);
                            drawMap(g, SpriteSheet, cellR[0], cellR[1], cellR[2], i, i1, camX, camY, camX_rs, camY_rs, camera, true, i2);

                        }

                    }
                }
            }
            else
            {
                for (int i = camX-camX_rs-1; i < camX+camX_rs; i++)
                {
                    for (int i1 = camY-camY_rs-1; i1 < camY+camY_rs; i1++)
                    {

                        cellR = cellSet(i, i1, cell,0);
                        drawMap(g, SpriteSheet, cellR[0], cellR[1], cellR[2], i, i1, camX, camY, camX_rs, camY_rs, camera,false,0);
                    }
                }
            }
        }

        private int[] cellSet(int iterator,int iterator1,int[] _cell,int iterator2)
        {
            int[] cell_;
            cell_ = new int[3];

            for (int i2 = 0; i2 < 3; i2++)
            {
                if (_cell[i2] == -1)
                {
                    cell_[i2] = iterator;
                }
                else
                {
                    if (_cell[i2] == -2)
                    {
                        cell_[i2] = iterator1;
                    }
                    else
                    {
                        cell_[i2] = _cell[i2]+iterator2;
                    }
                }
            }
            return cell_;
        }

        private void drawMap(Graphics g, Texture SpriteSheet, int cellX, int cellY, int cellZ, int cam_i, int cam_i1, int camX, int camY, int camX_rs, int camY_rs, Camera camera,boolean alpha,int cam_i2)
        {
            int item_width_c = item_width;
            float padding_camera_x = cam_i-(camX-camX_rs);
            float padding_camera_y = cam_i1-(camY-camY_rs);
            float alphaValue = 1f;

            if (alpha == true)
            {

                int padding_camera_z = 1 * cam_i2;

                if (cam_i2 != 0)
                {
                    padding_camera_x = padding_camera_x + padding_camera_z / 1.5f;
                    padding_camera_y = padding_camera_y + padding_camera_z / 1.5f;
                    alphaValue = 0.4f;
                }
            }

            if (map[cellX][cellY][cellZ].hasBomb == true)
            {
                drawItemTexture(g,screen_px+(int)(padding_camera_x*item_width_c)+camera.drawPadding,screen_py+(int)(padding_camera_y * item_width_c),5, 0,SpriteSheet,alphaValue);
            }

            if (map[cellX][cellY][cellZ].hasPowerUp == true)
            {
                drawItemTexture(g,screen_px+(int)(padding_camera_x*item_width_c)+camera.drawPadding,screen_py+(int)(padding_camera_y * item_width_c),6, map[cellX][cellY][cellZ].powerUp.type,SpriteSheet,alphaValue);
            }

            for (int i = 0; i < maxPlayers; i++)
            {
                Player iPlayer = playerList[i];

                if (cellX == iPlayer.x && cellY == iPlayer.y && cellZ == iPlayer.z) {
                    if (iPlayer.alive == true) {
                        drawItemTexture(g,screen_px+(int)(padding_camera_x*item_width_c)+camera.drawPadding,screen_py+(int)(padding_camera_y * item_width_c),4,iPlayer.color, SpriteSheet, alphaValue);
                    }
                }
            }
            drawItemTexture(g,screen_px+(int)(padding_camera_x*item_width_c)+camera.drawPadding,screen_py+(int)(padding_camera_y * item_width_c),map[cellX][cellY][cellZ].id,map[cellX][cellY][cellZ].dataTag,SpriteSheet,alphaValue);
            //drawItemTexture(g,screen_px+padding_camera_x*((int) item_width_c)+camera.drawPadding,screen_py+padding_camera_y *((int) item_width_c),map[cellX][cellY][cellZ].id,SpriteSheet,alphaValue);
        }

    }

    public Map(int _length,int _length_z,int _generatorId,int _maxPlayers)
    {
        length = _length;
        length_z = _length_z;

        map = new Item[length][length][length_z];

        maxPlayers = _maxPlayers;
        playerList = new Player[maxPlayers];

        generate(_generatorId);
    }

    public static void setItem(int _x, int _y, int _z, int _contain, int _dataTag, Bomb _bomb, boolean _hasBomb, PowerUp _powerUp, boolean _hasPowerUp)
    {
        Item item = new Item();

        item.id = _contain;
        item.dataTag = _dataTag;

        item.bomb = _bomb;
        item.hasBomb = _hasBomb;

        item.powerUp = _powerUp;
        item.hasPowerUp = _hasPowerUp;

        map[_x][_y][_z] = item;
    }

    private void itemMoveTick()
    {
        for (int i = 0; i < length; i++)
        {
            for (int i1 = 0; i1 < length; i1++)
            {
                for (int i2 = 0; i2 < length_z; i2++)
                {
                    if (map[i][i1][i2].hasBomb == true)
                    {
                        map[i][i1][i2].bomb.explode();
                    }

                    if (map[i][i1][i2].id == 3)
                    {
                        if (Time.fullTick-map[i][i1][i2].planted >= 8)
                        {
                            if (map[i][i1][i2].hasBomb == true)
                            {
                                map[i][i1][i2].bomb.iPlayer.bombCount -= 1;
                            }
                            map[i][i1][i2].id = 0;
                            map[i][i1][i2].hasBomb = false;
                        }
                    }
                }
            }
        }
    }

    public void actionLoop()
    {
        // player movement + collision
        for (int i = 0; i < maxPlayers; i++)
        {
            Player iPlayer = playerList[i];

            if (iPlayer.alive == true)
            {
                iPlayer.collision();
                iPlayer.moveForward(true);
            }
        }

        Time.tickMove();
        itemMoveTick();
    }

    public void draw(Graphics g, int _mode, Texture _texture)
    {
        for (int i = 0; i < maxPlayers; i++)
        {
            Player iPlayer = playerList[i];

            if (_mode == 0)
            {
                iPlayer.camera.drawMapOrder(g, _texture,iPlayer.camera,true);
            }
            else
            {
                iPlayer.camera.drawMapOrder(g, _texture,iPlayer.camera,false);
            }

        }
    }

    public void generate(int _generatorId)
    {
        // fill playerList
        for (int i = 0; i < maxPlayers; i++) {
            Player iPlayer;
            if (i == 0)
            {
                iPlayer =  new Player();
            }
            else
            {
                iPlayer =  new Player(length-2,length-2,length_z-2);
            }


            playerList[i] = iPlayer;
        }


        if (_generatorId == 1)
        {
            for (int i = 0; i < length; i++)
            {
                for (int i1 = 0; i1 < length; i1++)
                {
                    for (int i2 = 0; i2 < length_z; i2++)
                    {
                        Item item = new Item();
                        int random = (int)(Math.random() * 100 + 1);

                        if (random > 50)
                        {
                            if (random > 65)
                            {
                                item.id = 2;
                            }
                            else
                            {
                                random = (int)(Math.random() * 100 + 1);
                                item.id = 1;
                                if (random > 60)
                                {
                                    item.dataTag = 1;
                                }
                                else
                                {
                                    item.dataTag = 0;
                                }
                            }
                        }
                        else
                        {
                            item.id = 0;
                        }
                        map[i][i1][i2] = item;
                        System.out.println(map[i][i1][i2].id);
                    }
                }
            }
        }
        else
        {
            for (int i = 0; i < length; i++)
            {
                for (int i1 = 0; i1 < length; i1++)
                {
                    for (int i2 = 0; i2 < length_z; i2++)
                    {
                        Item item = new Item();

                        int random = (int)(Math.random() * 100 + 1);

                        item.id = 2;

                        if (i % 2 == 0 && i1 % 2 == 0)
                        {
                            item.id = 1;
                            if (random > 60)
                            {
                                item.dataTag = 1;
                            }
                            else
                            {
                                item.dataTag = 0;
                            }
                        }

					/*if (i == length-2 && i1 == length-2 && i2 == length_z-2)
					{
						item.id = 0;
					}*/

                        if (i > length-4 && i < length-1 && i1 > length-4 && i1 < length-1 && i2 == length_z-2)
                        {
                            if (i1 == length-3 && i == length-3)
                            {

                            }
                            else
                            {
                                item.id = 0;
                            }
                        }


                        if (i > 0 && i < 3 && i1 < 3 && i1 > 0 && i2 == 1)
                        {
                            if (i1 == 2 && i == 2)
                            {

                            }
                            else
                            {
                                item.id = 0;
                            }
                        }


                        if (i == 0 || i == length-1)
                        {
                            item.id = 1;
                            if (random > 60)
                            {
                                item.dataTag = 1;
                            }
                            else
                            {
                                item.dataTag = 0;
                            }
                        }

                        if (i1 == 0 || i1 == length-1)
                        {
                            item.id = 1;
                            if (random > 60)
                            {
                                item.dataTag = 1;
                            }
                            else
                            {
                                item.dataTag = 0;
                            }
                        }

                        if (i2 == 0 || i2 == length_z-1)
                        {
                            item.id = 1;
                            if (random > 60)
                            {
                                item.dataTag = 1;
                            }
                            else
                            {
                                item.dataTag = 0;
                            }
                        }



                        map[i][i1][i2] = item;
                        System.out.println(map[i][i1][i2].id);
                    }
                }
            }
        }
    }
}
