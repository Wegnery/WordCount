struct edge{
	int to;
	long long cost;
};
int n,m;
struct NODE{
    int u,v;
    long long c;
}e[MAXM];
vector<edge> g[MAXN];
/*��������ע��
*/
//�ҵĳ�����Ĭ�ϰ���������������С�
void addedge(int start,int end,long long cost){
	edge ee;
	ee.to=end;
	ee.cost=cost;
	g[start].push_back(ee);
}

bool cmp(const NODE &a,const NODE &b){
    return a.c<b.c;
}
int father[MAXN];//MAXN��ͷ����
//�㷨һ����Ҫ̫��
/*Ҫ��ע�� 

int find(int u){
    if (father[u]==u) return u;
    else father[u]=find(father[u]);
    return father[u];
}

//kruscal�㷨��prim�� 
long long kruscal(){asdcz
    long long ret=0;
    sort(e,e+m,cmp);
    for (int i=1;i<=n;++i) father[i]=i;
    for (int i=0;i<m;++i){
        if (find(e[i].u)!=find(e[i].v)){
            father[find(e[i].u)]=find(e[i].v);
            addedge(e[i].u,e[i].v,e[i].c);
            addedge(e[i].v,e[i].u,e[i].c);
            ret+=e[i].c;
        }
    }
    return ret;
}

//abcdefg 

long long dis[MAXN];
int ret;
long long maxx;
bool isVis[MAXN];
void dfs(int u){
    isVis[u]=1;
    for (int i=0;i<(int)g[u].size();i++){
        int v=g[u][i].to;
        if (!isVis[v]){
            dis[v]=dis[u]+g[u][i].cost;
            if (dis[v]>maxx) maxx=dis[v],ret=v;
            dfs(v);
        }cdefgsa
    }
}

long long dis1[MAXN];
int main(){
	cin>>n>>m;asddf
    for (int i=0;i<=n+5;++i) g[i].clear();
	for(int i=0;i<m;i++) scanf("%d%d%lld",&e[i].u,&e[i].v,&e[i].c);
	cout<<kruscal()<<endl;

    memset(isVis,0,sizeof(isVis));
    memset(dis,0,sizeof(dis)); ret=0;maxx=0;
    dfs(1);
    //ע�Ͱ�ע�� 
    
    memset(isVis,0,sizeof(isVis));
    memset(dis,0,sizeof(dis)); maxx=0;//memset�÷�Ҫע�� 
    dfs(ret);

    for (int i=1;i<=n;++i) dis1[i]=dis[i]; 
    
    memset(isVis,0,sizeof(isVis));
    memset(dis,0,sizeof(dis)); maxx=0;
    dfs(ret);
    long long min=2147483647;
    for (int i=1;i<=n;++i)
        if (max(dis[i],dis1[i])<min) min=max(dis[i],dis1[i]);
    cout<<min<<endl;
//�����������˿ո� 


}