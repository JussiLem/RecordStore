# -*- mode: ruby -*-
# vi: set ft=ruby :
Vagrant.configure("2") do |config|

  config.vm.box = "ubuntu/bionic64"
  # config.vm.box_check_update = false

  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.network "forwarded_port", guest: 5005, host: 5005
  config.vm.network "forwarded_port", guest: 3306, host: 3306

  config.vm.network "private_network", ip: "192.168.33.10"

  # config.vm.synced_folder "../data", "/vagrant_data"
  config.vm.provider "virtualbox" do |vb|
  #   # Display the VirtualBox GUI when booting the machine
  #   vb.gui = true
  #   # Customize the amount of memory on the VM:
    vb.memory = "1024"
  end

   config.vm.provision "shell", inline: <<-SHELL
    apt-get update
    sudo apt-get -y install \
        apt-transport-https \
        ca-certificates \
        curl \
        software-properties-common \
        git
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
    sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
    sudo apt update
    sudo apt-cache policy docker-ce
    sudo snap install docker
    cd /home/vagrant
    mkdir recordstore
    sudo git clone -b master https://github.com/JussiLem/RecordStore.git ./recordstore
    cd recordstore
    docker-compose up -d
   SHELL
end
