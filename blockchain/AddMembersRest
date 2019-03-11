import requests

import csv

from threading import Thread
import time


perfTimersChunk1 = []
perfTimersChunk2 = []
perfTimersChunk3 = []

def divide_chunks(l, n): 
      
    # looping till length l 
    for i in range(0, len(l), n):  
        yield l[i:i + n] 

def addMembers(chunk, threadPrefix):
    
    index = 0
    for val in chunk:
        
        index = index+1
        sus = str(threadPrefix)+str(index)
        
        url = 'http://localhost:3000/api/Member'
        data = '''{
          "$class": "br.unisinos.uhospital.ehr.Member",
          "cartaoSUS": "{sus}",
          "nome": "Thread {thread}",
          "dataNascimento": "2018-11-29T22:10:06.013Z",
          "prontuario": {
            "$class": "br.unisinos.uhospital.ehr.EHR",
            "id": "1",
            "descricao": "ECG: {ecg}",
            "formato": "ECG - Eletrocardiograma"
          }
        }'''.replace("{thread}", str(threadPrefix)).replace("{ecg}", str(val)).replace("{sus}", str(sus))
        
        headers = {
            'Content-type': 'application/json',
        }
        
        start = time.time()
        response = requests.post(url, headers=headers, data=data)
        end = time.time()
        timeTotal = (end - start)
        
        if response.status_code == 200:
            if threadPrefix == 1:
                perfTimersChunk1.append(sus + ","+ str(timeTotal) )
            elif threadPrefix == 2:
                perfTimersChunk2.append(sus + ","+ str(timeTotal) )
            elif threadPrefix == 3:
                perfTimersChunk3.append(sus + ","+ str(timeTotal) )
        else:
            raise Exception("Response error: "+ str(response.status_code) + " -" + str(response.content))


if __name__ == "__main__":
    
    ECG = []
    with open('ECGbench.csv') as csvfile:
        spamreader = csv.reader(csvfile, delimiter=' ', quotechar='|')
        for row in spamreader:
            ECG.append(', '.join(row))
            
    n = int(len(ECG) / 3)
    x = list(divide_chunks(ECG, n)) 
    
    thread1 = Thread(target=addMembers, args = (x[0], 1, ))
    thread2 = Thread(target=addMembers, args = (x[1], 2, ))
    thread3 = Thread(target=addMembers, args = (x[2], 3, ))
    
    thread1.start()
    thread2.start()
    thread3.start()
    
    thread1.join()
    thread2.join()
    thread3.join()
    
    allTimers = perfTimersChunk1 + perfTimersChunk2 + perfTimersChunk3
    
    with open('bench.csv', mode='w') as bench_file:
        timer_writter = csv.writer(bench_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        timer_writter.writerow(['SUS', 'Time'])
        for t in allTimers:
            timer_writter.writerow(t.split(","))
